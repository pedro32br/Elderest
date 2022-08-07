function MedicamentoItem(props) {
  return (
    <tr>
      <td>{props.id}</td>
      <td>{props.fornecedora}</td>
      <td>{props.substancia}</td>
      <td>{props.quantidade}</td>
      <td>{props.situacao}</td>
    </tr>
  );
}

export default MedicamentoItem;
