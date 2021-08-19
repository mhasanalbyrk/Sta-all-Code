import "./App.css";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Register from "./register";
import Login from "./login";
import Dashboard from "./dashboard";
import AdminDashboard from "./adminDashboard";
import AdminDashboardSeller from "./adminDashboardSeller";
import AdminDashboardProduct from "./adminDashboardProduct";

import "semantic-ui-css/semantic.min.css";
import "react-toastify/dist/ReactToastify.min.css";
import { ToastContainer } from "react-toastify";
import React from "react";

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route exact path="/">
            <Register></Register>
          </Route>
          <Route path="/login">
            <Login useStateWithLocalStorage={true} />
          </Route>
          <Route path="/dashboard">
            <Dashboard></Dashboard>
          </Route>
          <Route path="/adminDashboard">
            <AdminDashboard></AdminDashboard>
          </Route>
          <Route path="/adminDashboardSeller">
            <AdminDashboardSeller></AdminDashboardSeller>
          </Route>
          <Route path="/adminDashboardProduct">
            <AdminDashboardProduct></AdminDashboardProduct>
          </Route>
          <Route path="*"> 404</Route>
        </Switch>
      </Router>
      <ToastContainer />
    </div>
  );
}

export default App;
