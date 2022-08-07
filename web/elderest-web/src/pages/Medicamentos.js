import MedicamentoForm from '../components/MedicamentoForm';

import { useHistory } from 'react-router-dom';

function MedicamentosPage(props) {
    const history = useHistory();
    function addMedicamentoHandler(medicamentoDto) {
        fetch('http://localhost:8080/medicamento', {
            method: 'POST',
            body: JSON.stringify(medicamentoDto),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            history.replace('/');
        });
  }

  return (
    <section>
      <h1>Adicionar um medicamento</h1>
      <MedicamentoForm onAddMedicamento={addMedicamentoHandler} />
    </section>
  );
}

export default MedicamentosPage;
