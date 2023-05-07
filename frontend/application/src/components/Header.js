import React from 'react'

import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';

import iconeHome from '../assets/icon-home.png'

const Header = () => {
  return (
    <>
      <Navbar bg="dark" variant="dark">
        <Container>
          <Navbar.Brand href="#home">
            <img
              alt=""
              src={iconeHome}
              width="30"
              height="30"
              className="d-inline-block align-top"
            />{' '}
            E-mail sender
          </Navbar.Brand>
        </Container>
      </Navbar>
    </>
  );
  }
export default Header