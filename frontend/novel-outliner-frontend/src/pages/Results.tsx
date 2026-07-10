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
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
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


      <header className="bg-dark py-5">
        <div className="container px-5">
          <div className="text-center">
            <h1 className="display-5 fw-bolder text-white">
              {project.title}
            </h1>
          </div>
        </div>
      </header>


      <div className="container mt-5 bg-dark">
        <h3 className="text-white">Outline</h3>

        <strong className="text-white">
          Target Word Count: {project.targetTotalWordCount}
        </strong>

        <p className="text-white">
          You selected beat template:
          <strong> {project.beatTemplate.title}</strong>
        </p>


        <Link to="/outliner">
          Go Back
        </Link>


        <table className="table table-dark table-hover mt-4">
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

                <tr className="table-active border-bottom border-secondary">
                  <td colSpan={3}>
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
    </>
  );
}