import { describe, it, expect, vi, beforeEach } from "vitest";
import { render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { MemoryRouter } from "react-router-dom";
import Outliner from "./Outliner";

// Mock react-router-dom
const mockNavigate = vi.fn();

vi.mock("react-router-dom", async () => {
  const actual =
    await vi.importActual<typeof import("react-router-dom")>(
      "react-router-dom",
    );

  return {
    ...actual,
    useNavigate: () => mockNavigate,
  };
});

describe("Outliner", () => {
  beforeEach(() => {
    vi.clearAllMocks();

    globalThis.fetch = vi
      .fn()
      // Initial GET for beat templates
      .mockResolvedValueOnce({
        ok: true,
        json: async () => [
          {
            id: 1,
            title: "Save the Cat",
          },
          {
            id: 2,
            title: "Hero's Journey",
          },
        ],
      } as Response);
  });

  function renderPage() {
    return render(
      <MemoryRouter>
        <Outliner />
      </MemoryRouter>,
    );
  }

  it("renders the form", async () => {
    renderPage();

    expect(screen.getByLabelText(/Project Title/i)).toBeInTheDocument();

    expect(
      screen.getByRole("button", {
        name: /Generate Outline/i,
      }),
    ).toBeInTheDocument();
  });

  it("loads beat templates", async () => {
    renderPage();

    expect(await screen.findByText("Save the Cat")).toBeInTheDocument();

    expect(await screen.findByText("Hero's Journey")).toBeInTheDocument();
  });

  it("updates title input", async () => {
    const user = userEvent.setup();

    renderPage();

    const input = screen.getByLabelText(/Project Title/i);

    await user.type(input, "My Novel");

    expect(input).toHaveValue("My Novel");
  });

  it("selects a beat template", async () => {
    const user = userEvent.setup();

    renderPage();

    const template = await screen.findByText("Hero's Journey");

    await user.click(template);

    expect(screen.getByText(/Structure:/i)).toBeInTheDocument();

    expect(screen.getAllByText("Hero's Journey").length).toBeGreaterThan(0);
  });

  it("submits the form and navigates", async () => {
    const user = userEvent.setup();

    globalThis.fetch = vi
      .fn()
      // load templates
      .mockResolvedValueOnce({
        ok: true,
        json: async () => [
          {
            id: 1,
            title: "Save the Cat",
          },
        ],
      } as Response)

      // POST create project
      .mockResolvedValueOnce({
        ok: true,
        json: async () => ({
          id: 42,
        }),
      } as Response)

      // GET project
      .mockResolvedValueOnce({
        ok: true,
        json: async () => ({
          id: 42,
          title: "My Novel",
        }),
      } as Response);

    renderPage();

    await user.type(screen.getByLabelText(/Project Title/i), "My Novel");

    await user.click(await screen.findByText("Save the Cat"));

    await user.click(
      screen.getByRole("button", {
        name: /Generate Outline/i,
      }),
    );

    await waitFor(() => {
      expect(mockNavigate).toHaveBeenCalledWith(
        "/results",
        expect.objectContaining({
          state: expect.objectContaining({
            id: 42,
          }),
        }),
      );
    });

    expect(globalThis.fetch).toHaveBeenCalledTimes(3);
  });

  it("shows loading state while submitting", async () => {
    const user = userEvent.setup();

    let resolvePost: (value: Response) => void;

    globalThis.fetch = vi
      .fn()
      .mockResolvedValueOnce({
        ok: true,
        json: async () => [
          {
            id: 1,
            title: "Save the Cat",
          },
        ],
      } as Response)
      .mockImplementationOnce(
        () =>
          new Promise((resolve) => {
            resolvePost = resolve;
          }),
      );

    renderPage();

    await user.click(await screen.findByText("Save the Cat"));

    const button = screen.getByRole("button");

    await user.click(button);

    expect(button).toBeDisabled();
    expect(button).toHaveTextContent("Generating...");

    resolvePost!({
      ok: true,
      json: async () => ({
        id: 1,
      }),
    } as Response);
  });
});
