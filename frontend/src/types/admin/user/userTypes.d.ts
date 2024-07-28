export interface User {
    id?: number;
    firstName: string;
    lastName: string;
    email: string;
    phoneNumber: string;
    dateOfBirth: string; // TODO: Phone number validation
}