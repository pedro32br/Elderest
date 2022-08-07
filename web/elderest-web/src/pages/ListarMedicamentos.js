import { useState, useEffect } from "react";
import Spinner from "react-bootstrap/Spinner";

import MedicamentoList from "../components/medicamentos/MedicamentosList";

const DUMMY_DATA = [
  {
    id: "Adalat",
    fornecedora: "Bayer",
    quantidade: 5,
    substancia: "Nifedipino 10mg",
    situacao: "OK",
  },
  {
    id: "Xarelto",
    fornecedora: "Bayer",
    quantidade: 2,
    substancia: "Rivaroxabana 20mg",
    situacao: "OK",
  },
];

function ListarMedicamentosPage() {
  const [isLoading, setIsLoading] = useState(true);
  const [loadedDto, setLoadedDto] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    fetch("http://localhost:8080/medicamento/")
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        setIsLoading(false);
        setLoadedDto(data);
      });
  }, []);

  if (isLoading) {
    return (
      <section>
        <Spinner animation="border" />
      </section>
    );
  }

  return (
    <div>
      <h1>Lista de Medicamentos</h1>
      <MedicamentoList medicamentoDto={loadedDto} />
    </div>
  );
}

export default ListarMedicamentosPage;
