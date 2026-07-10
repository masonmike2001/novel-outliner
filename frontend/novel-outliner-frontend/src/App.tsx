import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home.tsx";
import Outliner from "./pages/Outliner.tsx";
import Results from "./pages/Results.tsx"
// import Dashboard from "./pages/Dashboard";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/outliner" element={<Outliner />} />
      <Route path="/results" element={<Results />} />
    </Routes>
  );
}

export default App;