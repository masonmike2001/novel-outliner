import { Link } from "react-router-dom";

export default function Home() {
  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark">
        <div className="container px-5">
          <Link className="navbar-brand fw-bold" to="/">
            Novel Outliner
          </Link>

          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item">
                <Link className="nav-link active" to="/">
                  Home
                </Link>
              </li>

              <li className="nav-item">
                <Link className="nav-link" to="/outliner">
                  Create Outline
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>


      {/* Hero */}
      <header className="py-5">
        <div className="container px-5">
          <div className="row justify-content-center">
            <div className="col-lg-9 text-center">

              <h1 className="display-3 fw-bold text-white mb-4">
                Generate a structured novel outline in seconds
              </h1>

              <p className="lead text-white-50 mb-4">
                Novel Outliner calculates chapter pacing, word distribution,
                and story beats using proven narrative frameworks.
              </p>

              <div className="d-flex justify-content-center gap-3">
                <Link
                  className="btn btn-primary btn-lg px-5"
                  to="/outliner"
                >
                  Create Outline
                </Link>
              </div>

            </div>
          </div>
        </div>
      </header>


      {/* Features */}
      <section className="py-5">
        <div className="container px-5">

          <h2 className="text-center text-white fw-bold mb-5">
            Built Features
          </h2>

          <div className="row g-4">

            <div className="col-lg-4">
              <div className="card bg-dark border-secondary h-100">
                <div className="card-body text-white">

                  <div className="feature bg-primary text-white rounded-3 mb-3 p-3 d-inline-block">
                    <i className="bi bi-diagram-3"></i>
                  </div>

                  <h3 className="h4">
                    Story Structure Engine
                  </h3>

                  <p className="text-white-50">
                    Generates outlines using Hero's Journey,
                    Three Act, and Four Act narrative models.
                  </p>

                </div>
              </div>
            </div>


            <div className="col-lg-4">
              <div className="card bg-dark border-secondary h-100">
                <div className="card-body text-white">

                  <div className="feature bg-success text-white rounded-3 mb-3 p-3 d-inline-block">
                    <i className="bi bi-calculator"></i>
                  </div>

                  <h3 className="h4">
                    Automated Pacing
                  </h3>

                  <p className="text-white-50">
                    Calculates chapter counts and distributes
                    target word counts across story segments.
                  </p>

                </div>
              </div>
            </div>


            <div className="col-lg-4">
              <div className="card bg-dark border-secondary h-100">
                <div className="card-body text-white">

                  <div className="feature bg-warning text-dark rounded-3 mb-3 p-3 d-inline-block">
                    <i className="bi bi-cloud"></i>
                  </div>

                  <h3 className="h4">
                    Full Stack Application
                  </h3>

                  <p className="text-white-50">
                    React + TypeScript frontend connected to
                    a Spring Boot REST API with persistent data storage.
                  </p>

                </div>
              </div>
            </div>

          </div>

        </div>
      </section>


      {/* Technology */}
      <section className="py-5">
        <div className="container px-5 text-center">

          <h2 className="text-white fw-bold mb-4">
            Technology Stack
          </h2>

          <div className="d-flex flex-wrap justify-content-center gap-3">

            {[
              "React",
              "TypeScript",
              "Spring Boot",
              "Java",
              "Hibernate",
              "PostgreSQL",
              "REST API",
              "Render Deployment"
            ].map((tech) => (
              <span
                key={tech}
                className="badge bg-primary fs-6 px-3 py-2"
              >
                {tech}
              </span>
            ))}

          </div>

        </div>
      </section>


      {/* Story Structures */}
      <section className="py-5">
        <div className="container px-5">

          <h2 className="text-center text-white fw-bold mb-5">
            Supported Story Frameworks
          </h2>

          <div className="row g-4">

            <div className="col-lg-4">
              <div className="card bg-dark border-secondary h-100">
                <div className="card-body text-white">
                  <h3 className="h4">
                    Hero's Journey
                  </h3>

                  <p className="text-white-50">
                    A 12-stage transformation arc focused on
                    adventure, trials, and character growth.
                  </p>

                </div>
              </div>
            </div>


            <div className="col-lg-4">
              <div className="card bg-dark border-secondary h-100">
                <div className="card-body text-white">

                  <h3 className="h4">
                    Three Act Structure
                  </h3>

                  <p className="text-white-50">
                    A classic beginning, middle, and end framework
                    with escalating conflict and resolution.
                  </p>

                </div>
              </div>
            </div>


            <div className="col-lg-4">
              <div className="card bg-dark border-secondary h-100">
                <div className="card-body text-white">

                  <h3 className="h4">
                    Four Act Structure
                  </h3>

                  <p className="text-white-50">
                    A pacing-focused framework that divides the story
                    into four major movements.
                  </p>

                </div>
              </div>
            </div>

          </div>

        </div>
      </section>


    </>
  );
}