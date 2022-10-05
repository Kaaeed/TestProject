import styles from "./Customer.module.css"

const Customer = (props) => {
    return (
        <li>
            <div>
                <p>{props.customer.firstName}</p>
            </div>
            <div>
                <p>{props.customer.lastName}</p>
            </div>
            <div>
                <p>{props.customer.email}</p>
            </div>
            <div>
                <p>{props.customer.phoneNumber}</p>
            </div>
            <div>
                <p>{props.customer.rentedBooks}</p>
            </div>
        </li>
    );
};

export  default Customer;