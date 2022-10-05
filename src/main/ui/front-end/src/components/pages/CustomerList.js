import {useEffect, useState} from "react";
import Customer from "../Customer";
import Card from "../ui/Card";


const CustomerList = () => {
    const [customers, setCustomers] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [hasError, setHasError] = useState();

    useEffect(() => {
        const customersData = async () => {
            const fetchedCustomers = await fetch("http://localhost:8080/LibraryUsers/person/all");
            if(!fetchedCustomers.ok){
                throw new Error("Http error has occurred.");
            }

            const customersTransformed = await fetchedCustomers.json();
            const loadedCustomers = [];

            for(const key in customersTransformed){
                loadedCustomers.push({
                    id: key,
                    personId: customersTransformed[key].personId,
                    firstName: customersTransformed[key].firstName,
                    lastName: customersTransformed[key].lastName,
                    email: customersTransformed[key].email,
                    phoneNumber: customersTransformed[key].phoneNumber,
                    rentedBooks: customersTransformed[key].rentedBooks
                })
            }
            setCustomers(loadedCustomers);
            setIsLoading(false);
        }
        customersData().catch(error => {
            setIsLoading(false);
            setHasError(error.message);
        });
    }, [customers]);

    if(hasError){
        return (
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

    const customerList = customers.map((customer) => {
        return <Customer key={customer.id} customer={customer}/>
    });

    return (
        <section>
            <Card>
                <ul>{customerList}</ul>
            </Card>
        </section>
    );
};

export default CustomerList;