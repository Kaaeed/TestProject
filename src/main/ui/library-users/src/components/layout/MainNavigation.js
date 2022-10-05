import styles from "./MainNavigation.module.css";
import {NavLink} from "react-router-dom";

const MainNavigation = () => {
    return (
        <header className={styles.header}>
            <div className={styles.logo}>Library system</div>
            <nav className={styles.nav}>
                <ul>
                    <li>
                        <NavLink to={"/all-customers"}
                                 className={(navData) => navData.isActive ? styles.active : ""}>
                            All customers
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to={"/new-customer"}
                                 className={(navData) => navData.isActive ? styles.active : ""}>
                            Add a customer
                        </NavLink>
                    </li>
                </ul>
            </nav>
        </header>
    );
};

export default MainNavigation;