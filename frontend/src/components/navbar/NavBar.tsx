import { Fragment } from "react";

import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';

function NavBar() {
    return (
        <Fragment>
                <Navbar expand="sm" className="bg-body-primary">
                    <Container>
                        <Navbar.Brand href="/dashboard">App</Navbar.Brand>
                        <Navbar.Toggle aria-controls="basic-navbar-nav" />
                        <Navbar.Collapse id="basic-navbar-nav">
                            <Nav className="me-auto">
                                <Nav.Link href="/shop">Shop</Nav.Link>
                                <NavDropdown title="Admin" id="basic-nav-dropdown">
                                    <NavDropdown.Item href="/admin/users">Users</NavDropdown.Item>
                                    <NavDropdown.Item href="/admin/inventory">Inventory</NavDropdown.Item>
                                </NavDropdown>
                            </Nav>
                        </Navbar.Collapse>
                    </Container>
                </Navbar>
        </Fragment>
    );
}

export default NavBar;