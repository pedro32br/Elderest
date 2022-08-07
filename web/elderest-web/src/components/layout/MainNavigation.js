import { Link } from "react-router-dom";

import { Navbar, Nav, NavDropdown, Container } from "react-bootstrap";

function MainNavigation() {
  return (
    <header>
      <Navbar collapseOnSelect variant="dark" bg="dark" expand="lg">
        <Container fluid>
          <Navbar.Brand as={Link} to="/">
            Elderest
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="responsive-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="me-auto">
              <Nav.Link as={Link} to="/medicamentos">
                Medicamentos
              </Nav.Link>
              <Nav.Link as={Link} to="/listar-medicamentos">
                Listar Medicamentos
              </Nav.Link>
              <Nav.Link as={Link} to="/medicos">
                Medicos
              </Nav.Link>
              <Nav.Link as={Link} to="/pacientes">
                Pacientes
              </Nav.Link>
            </Nav>
            <Nav>
              <Nav.Link href="/">Logout</Nav.Link>
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </header>
  );
}

export default MainNavigation;
