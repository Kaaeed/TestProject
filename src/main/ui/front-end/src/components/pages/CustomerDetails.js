import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import Card from "../ui/Card";
import Customer from "../Customer";

const CustomerDetails = () => {
    const [customer, setCustomer] = useState();
    const [isLoading, setIsLoading] = useState(true);
    const [hasError, setHasError] = useState();
    const params = useParams();

    useEffect(() => {
        const customerData = async () => {
            const fetchedCustomer = await fetch(`http://localhost:8080/LibraryUsers/person/${params.customerId}`);
            if(!fetchedCustomer.ok){
                throw new Error("Http error has occurred.");
            }

            const customerTransformed = await fetchedCustomer.json();
            setCustomer({
                id: 0,
                personId: customerTransformed.personId,
                firstName: customerTransformed.firstName,
                lastName: customerTransformed.lastName,
                email: customerTransformed.email,
                phoneNumber: customerTransformed.phoneNumber,
                rentedBooks: customerTransformed.rentedBooks
            });
            setIsLoading(false);
        };
        customerData().catch(error => {
           setIsLoading(false);
           setHasError(error.message);
        });
    }, [params.customerId]);

    if(hasError){
        return(
            <section>
                <Card>
                    <p>Error occurred.</p>
                    <p>{hasError}</p>
                </Card>
            </section>
        );
    }

    if(isLoading){
        return(
            <section>
                <Card>
                    <p>Loading...</p>
                </Card>
            </section>
        );
    }

    return(
        <section>
            <Card>
                <Customer key={customer.id} customer={customer}/>
            </Card>
        </section>
    );
};

export default CustomerDetails;