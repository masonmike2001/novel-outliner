
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
    const response = await fetch("https://my-backend-t5vb.onrender.com/api/projects", {
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
console.log("CREATE RESULT:", result);

const projectResponse = await fetch(
  `https://my-backend-t5vb.onrender.com/api/projects/${result.id}`
);

console.log("GET PROJECT STATUS:", projectResponse.status);

const project = await projectResponse.json();

console.log("GET PROJECT:", project);

navigate("/results", {
  state: project,
});
  }

    useEffect(() => {
async function loadBeatTemplates() {
  console.log("Loading from Render...");
  const response = await fetch(
    "https://my-backend-t5vb.onrender.com/api/templates"
  );

  console.log(response.url);

  const data = await response.json();
  console.log(data);

  setBeatTemplates(data);
}

  loadBeatTemplates();
}, []);


  return (
<>
<nav className="navbar navbar-expand-lg navbar-dark">
  <div className="container px-5">

    <Link className="navbar-brand text-white" to="/">
      Novel Outliner
    </Link>

    <div className="collapse navbar-collapse">
      <ul className="navbar-nav ms-auto">
        <li className="nav-item">
          <Link className="nav-link" to="/">
            Home
          </Link>
        </li>

        <li className="nav-item">
          <Link className="nav-link active" to="/outliner">
            Create Outline
          </Link>
        </li>
      </ul>
    </div>

  </div>
</nav>


<section className="py-5">

<div className="container px-5">


<div className="text-center mb-5">

<div className="feature bg-primary bg-gradient text-white rounded-3 mb-3 mx-auto">
<i className="bi bi-book"></i>
</div>

<h1 className="display-5 fw-bold text-white">
Create Your Novel Outline
</h1>

<p className="lead text-white-50">
Choose your goals and story structure.
Generate a complete chapter roadmap.
</p>

</div>



<div className="row justify-content-center">

<div className="col-lg-8">


<div className="card bg-dark border-secondary shadow-lg">

<div className="card-body p-5">


<form id="appForm" onSubmit={handleSubmit}>


<h4 className="text-white mb-4">
Project Details
</h4>



<div className="form-floating mb-4">

<input
className="form-control"
id="title"
type="text"
placeholder="Project title"
value={formData.title}
onChange={(e)=>
setFormData({
...formData,
title:e.target.value
})
}
/>

<label htmlFor="title">
Project Title
</label>

</div>




<div className="row">


<div className="col-md-6">

<div className="form-floating mb-4">

<input
className="form-control"
type="number"
value={formData.targetTotalWordCount}
onChange={(e)=>
setFormData({
...formData,
targetTotalWordCount:Number(e.target.value)
})
}
/>

<label>
Target Word Count
</label>

</div>

</div>



<div className="col-md-6">

<div className="form-floating mb-4">

<input
className="form-control"
type="number"
value={formData.chapterLength}
onChange={(e)=>
setFormData({
...formData,
chapterLength:Number(e.target.value)
})
}
/>

<label>
Chapter Length
</label>

</div>

</div>


</div>



<h4 className="text-white mt-3 mb-3">
Story Structure
</h4>


<div className="row g-3">

{beatTemplates.map((template)=>(

<div
className="col-md-4"
key={template.id}
>


<div
className={`card h-100 ${
formData.beatTemplate.id === template.id
? "border-primary bg-primary bg-opacity-25"
: "border-secondary bg-dark"
}`}

style={{
cursor:"pointer"
}}

onClick={()=>
setFormData({
...formData,
beatTemplate:{
id:template.id
}
})
}

>


<div className="card-body text-center">

<i className="bi bi-diagram-3 fs-2 text-primary"></i>

<h5 className="text-white mt-3">
{template.title}
</h5>


<p className="small text-white-50">
Narrative framework
</p>


</div>


</div>


</div>


))}


</div>




<div className="mt-4 p-4 bg-black rounded">


<h5 className="text-white">
Outline Preview
</h5>


<p className="text-white-50 mb-1">
Words:
<span className="text-white">
{" "}
{formData.targetTotalWordCount.toLocaleString()}
</span>
</p>


<p className="text-white-50 mb-1">
Chapter size:
<span className="text-white">
{" "}
{formData.chapterLength.toLocaleString()}
</span>
</p>



<p className="text-white-50">
Structure:
<span className="text-white">

{" "}
{
beatTemplates.find(
(t)=>t.id===formData.beatTemplate.id
)?.title || "Select a structure"
}

</span>
</p>


</div>




<button
className="btn btn-primary btn-lg w-100 mt-4"
type="submit"
>

<i className="bi bi-magic me-2"></i>

Generate Outline

</button>



</form>


</div>

</div>


</div>

</div>


</div>

</section>

</>
);
}