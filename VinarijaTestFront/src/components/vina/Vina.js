import React, { useState, useEffect, useCallback } from "react";
import { Row, Col, Button, Table, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import TestAxios from "../../apis/TestAxios";
import VinoRow from "./VinoRow";

const Vina = () => {
  const empty_search = {
    vinarijaPretraga: "",
    imePretraga: "",
  };

  const [search, setSearch] = useState(empty_search);
  const [totalPages, setTotalPages] = useState(0);
  const [pageNo, setPageNo] = useState(0);
  const [vina, setVina] = useState([]);
  const [vinarije, setVinarije] = useState([]);
  const [isChecked, setIsChecked] = useState(false);
  var navigate = useNavigate();

  const handleCheckboxChange = () => {
    setIsChecked(!isChecked);
  };

  const getVina = (newPageNo) => {

   
    const conf = {
      params: {
        vinarijaId: search.vinarijaPretraga,
        ime: search.imePretraga,
        pageNo: newPageNo,
      },
    };

    TestAxios.get("/vina", conf)
      .then((result) => {
        console.log(result);
        setPageNo(newPageNo);
        setTotalPages(result.headers["Total-Pages"]);
        console.log(totalPages);
        setVina(result.data);
      })
      .catch((error) => {
        console.log(error);
        alert("Error occured please try again!");
      });
  };

  const getVinarije = () => {
    TestAxios.get("/vinarije")
      .then((res) => {
        console.log(res);
        setVinarije(res.data);
       
      })
      .catch((error) => {
        console.log(error);
        alert("Error occured please try again!");
      });
  };

  useEffect(() => {
    getVina(0);
    getVinarije();
  }, []);

  const renderVina = () => {
    return vina.map((vino, index) => {
      if (window.localStorage.getItem("role") == "ROLE_KORISNIK" && vino.brojFlasa > 0) {
        return <VinoRow key={vino.id} vino={vino}></VinoRow>;
      }
      else if (window.localStorage.getItem("role") == "ROLE_ADMIN") {
        return <VinoRow key={vino.id} vino={vino}></VinoRow>;
      }
      
    });
  };

  const onInputChange = (event) => {
    const { name, value } = event.target;

    setSearch((prevSearch) => ({
      ...prevSearch,
      [name]: value,
    }));
  };

  const goToAdd = () => {
    navigate("/vina/add");
  };

  const renderSearchForm = () => {
    return (
      <>
        <Row>
          <Col>
            <Form.Group>
              <Form.Label>Ime</Form.Label>
              <Form.Control
                name="imePretraga"
                as="input"
                type="text"
                onChange={(e) => onInputChange(e)}
                placeholder="Unesite ime"
              ></Form.Control>
            </Form.Group>
          </Col>
          <Col>
            <Form.Group>
              <Form.Label>Vinarija</Form.Label>
              <Form.Select name="vinarijaPretraga" onChange={onInputChange}>
                <option value="">Izaberi vinariju</option>
                {vinarije.map((prev) => {
                  return (
                    <option value={prev.id} key={prev.id}>
                      {prev.ime}
                    </option>
                  );
                })}
              </Form.Select>
            </Form.Group>
          </Col>

          <Col>
            <Button className="mt-3" onClick={() => getVina(0)}>
              Search
            </Button>
          </Col>
        </Row>
      </>
    );
  };

  return (
    <div>
      <Col>
        <Row>
          <h1>Prikaz svih Vina</h1>
        </Row>
        <br />
        {window.localStorage.getItem("role") == "ROLE_ADMIN" ? (
          <Button onClick={() => goToAdd()}>Dodaj novo vino</Button>
        ) : null}

        <br />
        <br />
        <div>
          <label>
            <input
              type="checkbox"
              checked={isChecked}
              onChange={handleCheckboxChange}
            />
            Pretraga
          </label>
        </div>
        {isChecked && (
          <div>
            <Row>{renderSearchForm()}</Row>
          </div>
        )}

        <br />
        <Row>
          <Col>
            <Table id="vina-table">
              <thead>
                <tr>
                  <th>Ime vina</th>
                  {window.localStorage.getItem("role") == "ROLE_KORISNIK" ? (
                    <th>Opis</th>
                  ) : null}
                  <th>Godina Proizvodnje</th>
                  <th>Cena flase</th>
                  {window.localStorage.getItem("role") == "ROLE_ADMIN" ? (
                    <th>Broj flasa</th>
                  ) : null}
                  <th>Tip vina</th>
                  <th>Vinarija</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>{renderVina()}</tbody>
            </Table>
          </Col>
        </Row>
        <Button
          disabled={pageNo === 0}
          onClick={() => getVina(pageNo - 1)}
          className="mr-3"
        >
          Prev
        </Button>
        <Button
          disabled={pageNo === totalPages -1}
          onClick={() => getVina(pageNo + 1)}
        >
          Next
        </Button>
      </Col>
    </div>
  );
};

export default Vina;
