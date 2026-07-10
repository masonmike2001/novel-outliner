import { Link } from "react-router-dom";

export default function Home() {
  return(<>
<nav className="navbar navbar-expand-lg navbar-dark bg-dark">
    <div className="container px-5">
        <Link className="navbar-brand" to="/outliner">Novel Outliner</Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span className="navbar-toggler-icon"></span></button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                <li className="nav-item">
                    <Link className="nav-link active" aria-current="page" to="/">Story Structures</Link>
                </li>
                <li className="nav-item">
                    <Link className="nav-link" to="/outliner">Outliner</Link>
                </li>
            </ul>
        </div>
    </div>
</nav>

<header className="bg-dark py-5">
    <div className="container px-5">
        <div className="row gx-5 justify-content-center">
            <div className="col-lg-6">
                <div className="text-center my-5">
                    <h1 className="display-5 fw-bolder text-white mb-2">Story Structures</h1>
                    {/* <p className="lead text-white-50 mb-4">Explore the story structures below, and click the button once you decide which structure best fits your story.</p> */}
                    <div className="d-grid gap-3 d-sm-flex justify-content-sm-center">

                        {/* <Link className="btn btn-outline-light btn-lg px-4" to="https://www.mikemason.dev">www.mikemason.dev</Link> */}
                        <Link className="btn btn-primary btn-lg px-4 me-sm-3" to="/outliner">OUTLINE</Link>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
    <div className="container px-5 my-5">
        <div className="row gx-5">
            <div className="col-lg-4 mb-5 mb-lg-0">
                <div className="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i className="bi bi-signpost-2"></i></div>
                <h2 className="h4 fw-bolder text-white">Hero's Journey</h2>
                <p className="text-white">The classic 12-stage narrative arc. Follows a protagonist as they are pulled from their ordinary life into an unfamiliar realm of adventure, where they must overcome trials, face their greatest fears, and return transformed with a gift to improve their world.</p>
                <a className="text-decoration-none" href="https://www.storystructures.com/explore/herojourney">
                    Learn More
                    <i className="bi bi-arrow-right"></i>
                </a>
            </div>
            <div className="col-lg-4 mb-5 mb-lg-0">
                <div className="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i className="bi bi-collection"></i></div>
                <h2 className="h4 fw-bolder text-white">Three Act</h2>
                <p className="text-white">The foundational narrative framework. Raises the stakes through a series of obstacles and a major midpoint shift, and culminates in a climactic showdown where the story's core conflict is finally resolved.</p>
                <a className="text-decoration-none" href="https://www.storystructures.com/explore/threeact">
                    Learn More
                    <i className="bi bi-arrow-right"></i>
                </a>
            </div>
            <div className="col-lg-4 mb-5 mb-lg-0">
                <div className="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i className="bi bi-grid"></i></div>
                <h2 className="h4 fw-bolder text-white">Four Act</h2>
                <p className="text-white">A narrative framework that divides a story into four distinct quarters. This structure provides clear pacing milestones to keep the stakes rising and prevent the middle of the story from sagging.</p>
                <a className="text-decoration-none" href="https://www.storystructures.com/explore/fouract">
                    Learn More
                    <i className="bi bi-arrow-right"></i>
                </a>
            </div>

        </div>
    </div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/scripts.js"></script>
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
  </>);
}