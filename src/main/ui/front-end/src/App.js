import CustomerList from "./components/pages/CustomerList";
import Layout from "./components/layout/Layout";
import {Route, Routes, Navigate} from "react-router-dom";
import CustomerDetails from "./components/pages/CustomerDetails";
import NewCustomer from "./components/NewCustomer";

function App() {
  return (
      <Layout>
          <Routes>
              <Route path={"/"} element={<Navigate replace to={"/all-customers"}/>}/>
              <Route path={"/all-customers"} element={<CustomerList/>}/>
              <Route path={"/all-customers/:customerId"} element={<CustomerDetails/>}/>
              <Route path={"/new-customer"} element={<NewCustomer/>}/>
              {/*<Route path={"*"} element={<NotFound/>}/>*/}
          </Routes>
      </Layout>

  );
}

export default App;
