import Table from "react-bootstrap/Table";

import MedicamentoItem from "./MedicamentoItem";

function MedicamentoList(props) {
  return (
    <Table responsive="lg">
      <thead>
        <tr>
          <th>Nome do medicamento</th>
          <th>Fornecedora</th>
          <th>Substancia</th>
          <th>Quantidade</th>
          <th>Situacao</th>
        </tr>
      </thead>
      <tbody>
        {props.medicamentoDto.map((medicamentoDto) => (
          <MedicamentoItem
            key={medicamentoDto.id}
            id={medicamentoDto.id}
            fornecedora={medicamentoDto.fornecedora}
            substancia={medicamentoDto.substancia}
            quantidade={medicamentoDto.quantidade}
            situacao={medicamentoDto.situacao}
          />
        ))}
      </tbody>
    </Table>
  );
}

export default MedicamentoList;
