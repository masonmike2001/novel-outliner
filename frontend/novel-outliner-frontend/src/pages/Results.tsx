import { Link, useLocation } from "react-router-dom";
import React from "react";

export default function Results() {
  const location = useLocation();
  const project = location.state;
    console.log("PROJECT:", project);
    console.log("CHAPTERS:", project.chapters);
    console.log("SEGMENTS:", project.beatTemplate.beatSegments);
  if (!project) {
    return <div>No project data found</div>;

  }

  return (
    <>
      <nav className="navbar navbar-expand-lg navbar-dark">
        <div className="container px-5">
            
          <Link className="navbar-brand text-white" to="/outliner">
            Novel Outliner
          </Link>

          <div className="collapse navbar-collapse">
            <ul className="navbar-nav ms-auto">
              <li className="nav-item">
                <Link className="nav-link" to="/">
                  Story Structures
                </Link>
              </li>

              <li className="nav-item">
                <Link className="nav-link active" to="/outliner">
                  Outliner
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>

<div data-aos="fade-up">
<header className="py-5 text-center">
    <h1 className="display-3 fw-bold text-white">
        {project.title}
    </h1>

    <p className="lead text-secondary">
        Novel outline generated from the {project.beatTemplate.title} structure
    </p>
</header>
<Link
    className="btn btn-outline-light mb-4"
    to="/outliner"
>
    <i className="bi bi-arrow-left me-2"></i>
    Back
</Link>

      <div className="container py-5">
        <div className="card bg-dark border-secondary shadow-lg">
        <div className="card-body p-5">


        <div className="row g-4 mb-5">
<h5 className="text-white">Summary</h5>
<div className="col-md-4">
<div className="card bg-primary text-white shadow">
<div className="card-body">
<h6>Total Words</h6>
<h2>{project.targetTotalWordCount.toLocaleString()}</h2>
</div>
</div>
</div>

<div className="col-md-4">
<div className="card bg-success text-white shadow">
<div className="card-body">
<h6>Total Chapters</h6>
<h2>{project.chapters.length}</h2>
</div>
</div>
</div>

<div className="col-md-4">
<div className="card bg-warning text-dark shadow">
<div className="card-body">
<h6>Story Structure</h6>
<h5>{project.beatTemplate.title}</h5>
</div>
</div>
</div>

</div>

<div className="progress mb-4" style={{ height: "28px" }}>
  {project.beatTemplate.beatSegments.map((segment, index) => {
    const colors = [
      "bg-primary",
      "bg-success",
      "bg-info",
      "bg-warning",
      "bg-danger",
      "bg-secondary",
      "bg-dark"
    ];

    return (
      <div
        key={segment.id}
        className={`progress-bar ${colors[index % colors.length]}`}
        style={{
          width: `${(segment.endPercentage - segment.startPercentage) * 100}%`
        }}
        title={segment.title}
      >
      </div>
    );
  })}
</div>

<div className="d-flex flex-wrap gap-3 mt-3 justify-content-center">
  {project.beatTemplate.beatSegments.map((segment, index) => (
    <div key={segment.id} className="d-flex align-items-center">
      <span
        className={`badge bg-${[
          "primary",
          "success",
          "info",
          "warning",
          "danger",
          "secondary"
        ][index % 6]}`}
      >
        &nbsp;
      </span>

      <small className="ms-2 text-white">
        {segment.title}
      </small>
    </div>
  ))}
</div>
<hr />
<h5 className="text-white">Outline</h5>
        <table className="table table-dark table-striped table-hover align-middle">
          <thead>
            <tr>
              <th>Chapter</th>
              <th>Target Word Count</th>
              <th>Story Segment</th>
            </tr>
          </thead>


          <tbody>
            {project.beatTemplate.beatSegments.map((segment) => (
              <React.Fragment key={segment.id}>

                <tr className="bg-primary border-bottom border-secondary">
                  <td colSpan={3} className="py-3">
                    <span className="text-info fw-bold">
                      {segment.title}
                    </span>

                    <span className="small ms-2">
                      (
                      {segment.startPercentage * 100}%
                      {segment.startPercentage !== segment.endPercentage &&
                        ` to ${segment.endPercentage * 100}%`
                      }
                      )
                    </span>
                  </td>
                </tr>


{project.chapters
  .filter(
    (chapter) =>
      chapter.beatSegment &&
      chapter.beatSegment.id === segment.id
  )
  .map((chapter) => (
    <tr key={chapter.id}>
      <td className="text-white ps-4">
        Chapter {chapter.sequenceOrder}
      </td>

      <td className="text-white">
        {chapter.wordCount.toLocaleString()}
      </td>

      <td className="text-white small">
        {segment.title}
      </td>
    </tr>
  ))
}

              </React.Fragment>
            ))}
          </tbody>
        </table>
        </div>
</div>
</div>
      </div>
    </>
  );
}