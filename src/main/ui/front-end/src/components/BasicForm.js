const BasicForm = () => {
    return (
        <form>
            <div>
                <div>
                    <label htmlFor={"firstName"}>First Name</label>
                    <input type={"text"} id={"firstName"}/>
                </div>
                <div>
                    <label htmlFor={"lastName"}>Last Name</label>
                    <input type={"text"} id={"lastName"}/>
                </div>
            </div>
            <div>
                <div>
                    <label htmlFor={"emailAddress"}>E-mail</label>
                    <input type={"email"} id={"emailAddress"}/>
                </div>
                <div>
                    <label htmlFor={"phoneNumber"}>Phone number</label>
                    <input type={"number"} id={"phoneNumber"}/>
                </div>
                <div>
                    <label htmlFor={"rentedBooks"}>Rented books</label>
                    <input type={"number"} id={"rentedBooks"}/>
                </div>
            </div>
        </form>
    );
};

export default BasicForm;