
import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect,  } from "react";


export default function Outliner() {
const navigate = useNavigate();
const [formData, setFormData] = useState<{
  title: string;
  targetTotalWordCount: number;
  chapterLength: number;
  beatTemplate: {
    id: number | null;
  };
}>({
  title: "",
  targetTotalWordCount: 100000,
  chapterLength: 4000,
  beatTemplate: {
    id: null
  }
});

  const [beatTemplates, setBeatTemplates] = useState<
  { id: number; title: string }[]
>([]);
    async function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
console.log(formData);
    const response = await fetch("/api/projects", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    });

    if (!response.ok) {
      throw new Error("Failed to create project");
    }

    const result = await response.json();

const projectResponse = await fetch(`/api/projects/${result.id}`);

if (!projectResponse.ok) {
  throw new Error("Failed to load project");
}

const project = await projectResponse.json();

navigate("/results", {
  state: project,
});
  }

    useEffect(() => {
  async function loadBeatTemplates() {
    const response = await fetch("/api/templates");
    const data = await response.json();

    setBeatTemplates(data);
  }

  loadBeatTemplates();
}, []);


  return (<>
  
<nav className="navbar navbar-expand-lg navbar-dark bg-dark">
    <div className="container px-5">
        <Link className="navbar-brand text-white"  to="/outliner">Novel Outliner</Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span className="navbar-toggler-icon"></span></button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                <li className="nav-item">
                    <Link className="nav-link" aria-current="page" to="/">Story Structures</Link>
                </li>
                <li className="nav-item"><Link className="nav-link active" to="/outliner">Outliner</Link></li>

            </ul>
        </div>
    </div>
</nav>
<section className="bg-dark py-5">
    <div className="container px-5 my-5 px-5">
        <div className="text-center mb-5">
            <div className="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i className="bi bi-book"></i></div>
            <h2 className="fw-bolder text-white">Calculate optimal word count</h2>
            <p className="lead mb-0 text-white">Choose a story structure, word targets, and we'll do the rest.</p>
        </div>
        <div className="row gx-5 justify-content-center">
            <div className="col-lg-6">
                <form id="appForm" onSubmit={handleSubmit}> 
                    <div className="form-floating mb-3">
                        <input
                            className="form-control"
                            id="title"
                            type="text"
                            value={formData.title}
                            onChange={(e) =>
                                setFormData({
                                ...formData,
                                title: e.target.value,
                                })
                            }
                            />
                        <label htmlFor="title">Project title</label>
                        
                    </div>
                    <div className="form-floating mb-3">
                        <input
                        className="form-control"
                        id="targetTotalWordCount"
                        type="number"
                        value={formData.targetTotalWordCount}
                        onChange={(e) =>
                            setFormData({
                            ...formData,
                            targetTotalWordCount: Number(e.target.value),
                            })
                        }
                        />
                        <label htmlFor="email">Target total word count</label>
                        
                    </div>
                    <div className="form-floating mb-3">
                        <input
                        className="form-control"
                        id="chapterLength"
                        type="number"
                        value={formData.chapterLength}
                        onChange={(e) =>
                            setFormData({
                            ...formData,
                            chapterLength: Number(e.target.value),
                            })
                        }
                        />
                        <label htmlFor="chapter">Target chapter word count</label>
                       
                    </div>
                    <label>
<select
  className="form-select"
  value={formData.beatTemplate.id ?? ""}
  onChange={(e) =>
    setFormData({
      ...formData,
      beatTemplate: {
        id: Number(e.target.value)
      }
    })
  }
>
  <option value="">Choose a template</option>

  {beatTemplates.map((template) => (
    <option key={template.id} value={template.id}>
      {template.title}
    </option>
  ))}
</select>
                    </label>
                    <div className="d-grid"><button className="btn btn-primary btn-lg" id="calcButton" type="submit">Create</button></div>
                </form>
            </div>
        </div>
    </div>
</section>
  </>);
}