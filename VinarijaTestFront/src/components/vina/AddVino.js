import React, { useState, useEffect, useCallback } from "react";
import { Row, Col, Button, Table, Form } from "react-bootstrap";
import TestAxios from "../../apis/TestAxios";
import { useNavigate } from "react-router-dom";

const AddVino = () => {

    var vino = {
        ime: "",
        opis: "",
        godinaProizvodnje: -1,
        cenaFlasa: -1,
        brojFlasa: 0,
        tipId: '',
        tipIme: '',
        vinarijaId: -1,
        vinarijaIme: '',
      };

    const [addVino, setAddVino] = useState(vino);
    const [tipovi, setTipovi] = useState([]); 
    const [vinarije, setVinarije] = useState([]); 
    var navigate = useNavigate();

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

    const getTipovi = () => {
        TestAxios.get("/tipovi")
          .then((res) => {
            console.log(res);
            setTipovi(res.data);
          })
          .catch((error) => {
            console.log(error);
            alert("Error occured please try again!");
          });
      };  

      useEffect(() => {
        getVinarije();
        getTipovi();
      }, []);

      const valueInputChanged = (e) => {
        let input = e.target;
        let name = input.name;
        let value = input.value;
    
        let zadatakFromState = { ...addVino };
        zadatakFromState[name] = value;
        console.log(e.target.value)
        setAddVino(zadatakFromState);
      };

      const onTipChange = (e) => {
        const value = e.target.value;
        const selectedPrev = tipovi.find((prev) => prev.id === parseInt(value));
        if (selectedPrev) {
            setAddVino((prev) => ({
            ...prev,
            tipId: selectedPrev.id,
            tipIme: selectedPrev.ime,
            
          }));
        }
        console.log(selectedPrev.id + selectedPrev.ime)
      };

      const onVinarijaChange = (e) => {
        const value = e.target.value;
        const selectedPrev = vinarije.find((prev) => prev.id === parseInt(value));
        if (selectedPrev) {
            setAddVino((prev) => ({
            ...prev,
            vinarijaId: selectedPrev.id,
            vinarijaIme: selectedPrev.ime,
            
          }));
        }
        console.log(selectedPrev.id + selectedPrev.ime)
      };




      const create = (e) => {
        e.preventDefault();
      
         if (
          !addVino.ime ||
          !addVino.opis ||
          addVino.godinaProizvodnje < 0 ||
          addVino.cenaFlasa < 0 ||
          addVino.tipId === -1 ||
          !addVino.tipIme ||
          addVino.vinarijaId === -1 ||
          !addVino.vinarijaIme
        ) {
          alert("Molimo Vas popunite sva obavezna polja.");
          return;
        }

        var params = {
            'ime': addVino.ime,
            'opis': addVino.opis,
            'godinaProizvodnje': addVino.godinaProizvodnje,
            'cenaFlase': addVino.cenaFlasa,
            'brojFlasa': addVino.brojFlasa,
            'tipId': addVino.tipId,
            'tipIme': addVino.tipIme,
            'vinarijaId': addVino.vinarijaId,
            'vinarijaIme': addVino.vinarijaIme
        };
    
        TestAxios.post('/vina', params)
        .then(res => {
            console.log(res);
            alert('Vina was added successfully!');
            navigate('/vina'); 
        })
        .catch(error => {
            console.log(error);
            alert('Error occured please try again!');
         });
    }

      return (
        
        <>
        <Row class="row align-self-start">
          <Col></Col>
    
          <Col xs="12" sm="10" md="8">
            <h1>Dodaj vino</h1>
            <Form>
    
              <Form.Group>
                <Form.Label htmlFor="ime">Ime:</Form.Label>
                <Form.Control
                  id="ime"
                  name="ime"
                  type="text"
                  onChange={(e) => valueInputChanged(e)}
                />{" "}
    
              </Form.Group>
              <Form.Group>
                <Form.Label htmlFor="opis">opis:</Form.Label>
                <Form.Control
                  type="text"
                  id="opis"
                  name="opis"
                  onChange={(e) => valueInputChanged(e)}
                />{" "}
              </Form.Group>
    
              <Form.Group>
                <Form.Label htmlFor="godinaProizvodnje">Godina Proizvodnje:</Form.Label>
                <Form.Control
                  type="text"
                  id="godinaProizvodnje"
                  name="godinaProizvodnje"
                  onChange={(e) => valueInputChanged(e)}
                />
              </Form.Group>

              <Form.Group>
                <Form.Label htmlFor="cenaFlasa">Cena flase:</Form.Label>
                <Form.Control
                  type="number"
                  id="cenaFlasa"
                  name="cenaFlasa"
                  onChange={(e) => valueInputChanged(e)}
                />
              </Form.Group>

              
    
              <Row>
                <Col>
                  <Form.Group>
                    <Form.Label>Tip:</Form.Label>
                    <Form.Select name="tipPretraga" onChange={onTipChange}>
                      <option value="">Izaberi tip</option>
                      {tipovi.map((prev) => {
                        return (
                          <option value={prev.id} key={prev.id}>
                            {prev.ime}
                          </option>
                        );
                      })}
                    </Form.Select>
                  </Form.Group>
                </Col>
              </Row>

              <Row>
                <Col>
                  <Form.Group>
                    <Form.Label>Vinarije:</Form.Label>
                    <Form.Select name="vinarijePretraga" onChange={onVinarijaChange}>
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
              </Row>
    
              <Button type="submit" onClick={create}>
                Dodaj
              </Button>
            </Form>
          </Col>
          <Col></Col>
        </Row>
        <br></br>
      </>
    
        )

}

export default AddVino;