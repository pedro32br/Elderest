import React, { useState } from "react";
import { Route, Switch } from "react-router-dom";

import LoginForm from "./components/LoginForm";
import MedicamentosPage from "./pages/Medicamentos";
import Dashboard from "./pages/Dashboard";
import MainNavigation from "./components/layout/MainNavigation";
import ListarMedicamentosPage from './pages/ListarMedicamentos'

function App() {
  const adminUser = {
    email: "admin@puc2021.com",
    password: "admin123",
  };

  const [user, setUser] = useState({ email: "" });
  const [error, setError] = useState("");

  const Login = (details) => {
    console.log(details);

    if (
      details.email == adminUser.email &&
      details.password == adminUser.password
    ) {
      setUser({
        name: details.name,
        email: details.email,
      });
    } else {
      setError("Usuário ou senha inválidos");
    }
  };

  const Logout = (setUser, setError) => {
    setUser({ email: "" });
    setError("");
  };

  return (
    <div className="App">
      {user.email != "" ? (
        <div>
          <MainNavigation Logout={Logout} />
          <Switch>
            <Route path="/" exact>
              <Dashboard />
            </Route>
            <Route path="/medicamentos">
              <MedicamentosPage />
            </Route>
            <Route path="/listar-medicamentos">
              <ListarMedicamentosPage />
            </Route>
          </Switch>
        </div>
      ) : (
        <LoginForm Login={Login} error={error} />
      )}
    </div>
  );
}

export default App;
