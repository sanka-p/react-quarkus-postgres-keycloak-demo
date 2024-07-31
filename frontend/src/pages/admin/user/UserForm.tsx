import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import { User } from '../../../types/admin/user/UserTypes';
import React from 'react';

interface UserFormProps {
    formData: User | null;
    handleChange: (event: React.ChangeEvent<HTMLInputElement>) => void;
    isDisabled: boolean;
}

function UserForm(
        {
            formData,
            handleChange,
            isDisabled
        }: UserFormProps
    ) {
    
    return (
        <React.Fragment>
            <Container>
                <Form>
                <Form.Group className="mb-3">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control name="firstName" onChange={handleChange} value={formData?.firstName} disabled={isDisabled}/>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control name="lastName" onChange={handleChange} value={formData?.lastName} disabled={isDisabled}/>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Email</Form.Label>
                    <Form.Control name="email" onChange={handleChange} value={formData?.email} disabled={isDisabled}/>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Phone Number</Form.Label>
                    <Form.Control name="phoneNumber" onChange={handleChange} value={formData?.phoneNumber} disabled={isDisabled}/>
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label>Date of Birth</Form.Label>
                    <Form.Control type="date" name="dateOfBirth" onChange={handleChange} value={formData?.dateOfBirth} disabled={isDisabled}/>
                </Form.Group>
                </Form>
            </Container>
        </React.Fragment>
    );
}

export default UserForm;