import React from 'react'

import { useState } from 'react';

import { Form, Button, Card, Col, Row, Alert } from 'react-bootstrap';

import { useFetch } from '../hooks/useFetch';

import Loading from '../components/Loading';

const WriteEmail = () => {

  const [checked, setChecked] = useState(false);

  const { postData: postSimpleEmail, loading: loadingOne } = useFetch("/sending");
  const { postData: postEmailAttachment, loading: loadingTwo } = useFetch("/sending/attachment");

  const formRef = {
    emailFrom: 'test@gmail.com',
    emailTo: '',
    subject: '',
    text: ''
  }

  const [form, setForm] = useState(formRef);

  const handleCheckboxChange = () => {
    setChecked(!checked); // Inverte o valor do estado ao alterar o checkbox
  };

  const handleSubmit = (e) => {
    if (!checked) {
      postSimpleEmail(form)
    } else {
      postEmailAttachment(form)
    }
    e.preventDefault();
    console.log(form);
  }

  return (
    <>
      {loadingOne && <Loading />}
      {loadingTwo && <Loading />}
      <Card style={{ height: "91vh" }}>
        <Card.Header>
          <Card.Title>
            Novo E-mail
          </Card.Title>
        </Card.Header>
        <Card.Body className="pl-5 pr-5">
          <Form className="ml-4" onSubmit={handleSubmit}>
            <Row className="mb-4 ">
              <Row>
                <Col className="col-md-6 col-12">
                  <Form.Group className="mb-4" controlId="formPercent">
                    <Form.Label>E-mail from</Form.Label>
                    <Form.Control
                      type="email"
                      placeholder="test@gmail.com"
                      onChange={(e) => { setForm({ ...form, emailFrom: e.target.value }) }}
                      value={form.emailFrom}
                      readOnly="true"
                      required />
                  </Form.Group>
                </Col>
                <Col className="col-md-6 col-12">
                  <Form.Group className="mb-4" controlId="formName">
                    <Form.Label>E-mail To</Form.Label>
                    <Form.Control
                      type="email"
                      placeholder="E-mail de destino"
                      onChange={(e) => { setForm({ ...form, emailTo: e.target.value }) }}
                      value={form.emailTo}
                      required />
                  </Form.Group>
                </Col>
              </Row>
              <Row>
                <Col className="col-12">
                  <Form.Group className="mb-3" controlId="formFinalDate">
                    <Form.Label>Subject</Form.Label>
                    <Form.Control
                      type="text"
                      placeholder="Assunto do e-mail"
                      onChange={(e) => { setForm({ ...form, subject: e.target.value }) }}
                      value={form.subject}
                    />
                  </Form.Group>
                </Col>
                <Col>
                  <Form.Check
                    type="checkbox"
                    label="Anexar documento"
                    checked={checked}
                    onChange={handleCheckboxChange}
                  />
                </Col>
              </Row>
              <Row>
                <Col className="col-12">
                  <Form.Group className="mb-3" controlId="formDescription">
                    <Form.Label>Content</Form.Label>
                    <Form.Control
                      as="textarea"
                      rows={5}
                      placeholder="Conteúdo do e-mail..."
                      onChange={(e) => { setForm({ ...form, text: e.target.value }) }}
                      value={form.text}
                      required />
                  </Form.Group>
                </Col>
              </Row>
            </Row>
            <div className="text-center">
              <Button type="submit" variant="danger" className="col-sm-3 mx-2">
                Send
              </Button>
            </div>
          </Form>
        </Card.Body>
        <Card.Footer className="d-flex justify-content-center">
        </Card.Footer>
      </Card>
    </>
  )
}

export default WriteEmail