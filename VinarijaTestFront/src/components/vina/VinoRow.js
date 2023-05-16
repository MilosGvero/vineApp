import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Button, Form, Col, Row } from "react-bootstrap";
import TestAxios from "../../apis/TestAxios";

const VinoRow = (props) => {
  var navigate = useNavigate();
  const [kolicina, setKolicina] = useState("");
  const [kupovina, setKupovina] = useState("");

  const valueInputChanged = (e) => {
    console.log(e.target.value);
    setKolicina(e.target.value);
  };

  const valueInputChangedDva = (e) => {
    console.log(e.target.value);
    setKupovina(e.target.value);
  }

  const deleteVino = (id) => {
    TestAxios.delete("/vina/" + id)
      .then((res) => {
        console.log(res);
        alert("Vino was deleted successfully!");
        window.location.reload();
      })
      .catch((error) => {
        console.log(error);
        alert("Error occured please try again!");
      });
  };

  const narucivanje = (id) => {
    TestAxios.post(`/vina/${id}/naruci?kolicinaParam=${kolicina}`)
      .then((res) => {
        console.log(res);
        alert("Uspesno ste narucili!");
        window.location.reload();
      })
      .catch((error) => {
        console.log(error);
        alert("Error occured please try again!");
      });
  };

  const kupovinaVina = (id) => {
    TestAxios.post(`/vina/${id}/kupi?kolicinaParam=${kupovina}`)
      .then((res) => {
        console.log(res);
        alert("Uspesno ste narucili!");
        window.location.reload();
      })
      .catch((error) => {
        console.log(error);
        alert("Error occured please try again!");
      });
  };

  return (
    <tr key={props.vino.id}>
      <td>{props.vino.ime}</td>
      {window.localStorage.getItem("role") == "ROLE_KORISNIK" ? (
        <td>{props.vino.opis}</td>
      ) : null}
      <td>{props.vino.godinaProizvodnje}</td>
      <td>{props.vino.cenaFlase}</td>
      {window.localStorage.getItem("role") == "ROLE_ADMIN" ? (
        <td>{props.vino.brojFlasa}</td>
      ) : null}
      <td>{props.vino.tipIme}</td>
      <td>{props.vino.vinarijaIme}</td>
  
      {window.localStorage.getItem("role") === "ROLE_ADMIN" && (
        <>
          <td>
            <Button
              className="button button-navy"
              onClick={() => deleteVino(props.vino.id)}
            >
              Delete
            </Button>
          </td>
          {props.vino.brojFlasa < 10 && (
            <td>
              <Row>
                <Col>
                  <Form.Group>
                    <Form.Control
                      name="kolicina"
                      as="input"
                      type="number"
                      id="kolicina"
                      style={{ width: "70px" }}
                      onChange={(e) => valueInputChanged(e)}
                      required
                    />
                  </Form.Group>
                </Col>
                <Col>
                  <Button
                    className="button button-navy"
                    onClick={() => narucivanje(props.vino.id)}
                  >
                    Naruci
                  </Button>
                </Col>
              </Row>
            </td>
          )}
        </>
      )}
  
      {window.localStorage.getItem("role") === "ROLE_KORISNIK" && (
        <td>
          <Row>
            <Col>
              <Form.Group>
                <Form.Control
                  name="kupovina"
                  as="input"
                  type="number"
                  id="kolicina"
                  style={{ width: "70px" }}
                  onChange={(e) => valueInputChangedDva(e)}
                  required
                />
              </Form.Group>
            </Col>
            <Col>
              <Button
                className="button button-navy"
                onClick={() => kupovinaVina(props.vino.id)}
              >
                Kupi
              </Button>
            </Col>
          </Row>
        </td>
      )}
    </tr>
  );
};

export default VinoRow;
