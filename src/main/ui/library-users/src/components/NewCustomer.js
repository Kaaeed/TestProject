import Card from "./ui/Card";
import {useRef} from "react";

import styles from "./NewCustomer.module.css";
import {useNavigate} from "react-router-dom";

const NewCustomer = () => {
    const firstNameInputRef = useRef();
    const lastNameInputRef = useRef();
    const emailInputRef = useRef();
    const phoneNumberInputRef = useRef();
    const rentedBooksInputRef = useRef();
    const navigate = useNavigate();

    const addCustomer = async (newCustomer) => {
        const response = await fetch("http://localhost:8080/LibraryUsers/person/", {
            method: 'POST',
            body: JSON.stringify(newCustomer),
            headers: {
                'Content-Type': 'application/json'
            },
        });

        const data = await response.json();

        if(!response.ok){
            throw new Error(data.message || "Could not create a new user.");
        }
    };

    function submitHandler(event) {
        event.preventDefault();
        const newCustomer = {
            firstName: firstNameInputRef.current.value,
            lastName: lastNameInputRef.current.value,
            email: emailInputRef.current.value,
            phoneNumber: phoneNumberInputRef.current.value,
            rentedBooks: rentedBooksInputRef.current.value
        }

        addCustomer(newCustomer);
        navigate("/all-customers", {replace:true});
    }

    return (
        <Card>
            <form onSubmit={submitHandler} className={styles.form}>
                <div className={styles.control}>
                    <label htmlFor={"firstName"}>First name</label>
                    <input type={"text"} id={"firstName"} ref={firstNameInputRef}/>
                </div>
                <div className={styles.control}>
                    <label htmlFor={"lastName"}>Last name</label>
                    <input type={"text"} id={"lastName"} ref={lastNameInputRef}/>
                </div>
                <div className={styles.control}>
                    <label>Email</label>
                    <input type={"email"} id={"email"} ref={emailInputRef}/>
                </div>
                <div className={styles.control}>
                    <label htmlFor={"phoneNumber"}>Phone number</label>
                    <input type={"number"} id={"phoneNumber"} ref={phoneNumberInputRef}/>
                </div>
                <div className={styles.control}>
                    <label htmlFor={"rentedBooks"}>Number of rented books</label>
                    <input type={"number"} id={"rentedBooks"} ref={rentedBooksInputRef}/>
                </div>
                <div className={styles.actions}>
                    <button className={"btn"}>Add customer</button>
                </div>
            </form>
        </Card>
    );
};

export default NewCustomer;