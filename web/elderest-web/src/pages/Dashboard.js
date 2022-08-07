import logo from "../assets/elderest.gif";

import { Image, Container, Row, Col } from "react-bootstrap";

function Dashboard() {
  return (
    <Container>
      <Row>
        <Col sm={8}>
          <li>Bem-vindo admin@puc2021.com</li>
              </Col>
              <Col sm={4}>
          <Image src={logo} alt="logo" thumbnail={true}></Image>
        </Col>
      </Row>
    </Container>
  );
}

export default Dashboard;
