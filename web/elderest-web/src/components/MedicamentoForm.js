import { Form, Row, Col, Button } from "react-bootstrap";
import { useRef } from "react";

function MedicamentoForm(props) {
  const idInputRef = useRef();
  const fornecedoraInputRef = useRef();
  const substanciaInputRef = useRef();
  const quantidadeInputRef = useRef();

  function submitHandler(event) {
    event.preventDefault();

    const enteredId = idInputRef.current.value;
    const enteredFornecedora = fornecedoraInputRef.current.value;
    const enteredSubstancia = substanciaInputRef.current.value;
    const enteredQuantidade = quantidadeInputRef.current.value;

    const medicamentoDto = {
      id: enteredId,
      fornecedora: enteredFornecedora,
      substancia: enteredSubstancia,
      quantidade: enteredQuantidade,
    };

    props.onAddMedicamento(medicamentoDto);
  }
  return (
    <Form onSubmit={submitHandler}>
      <Row className="mb-3">
        <Form.Group as={Col} controlId="formGridId">
          <Form.Label>Nome do farmaco</Form.Label>
          <Form.Control type="text" placeholder="Remedio" ref={idInputRef} />
        </Form.Group>

        <Form.Group as={Col} controlId="formGridFornecedora">
          <Form.Label>Empresa Fornecedora do Farmaco</Form.Label>
          <Form.Control
            type="text"
            placeholder="Bayer, Johnson, Neofarma..."
            ref={fornecedoraInputRef}
          />
        </Form.Group>
      </Row>

      <Form.Group className="mb-3" controlId="formGridSubstancia">
        <Form.Label>Substancia</Form.Label>
        <Form.Control
          type="text"
          placeholder="Substancia"
          ref={substanciaInputRef}
        />
      </Form.Group>

      <Row className="mb-3">
        <Form.Group as={Col} controlId="formGridQuantidade">
          <Form.Label>Quantidade</Form.Label>
          <Form.Control type="number" ref={quantidadeInputRef} />
        </Form.Group>
      </Row>

      <Button variant="primary" type="submit">
        Submit
      </Button>
    </Form>
  );
}

export default MedicamentoForm;
