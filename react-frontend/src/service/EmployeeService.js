import axios from 'axios'

const EMPLOYEE_SERVICE_URL = "http://localhost:8080/employee-service/api/employees";

const EMPLOYEE_ID = 1;

class EmployeeService {

    getEmployee() {

        return axios.get(EMPLOYEE_SERVICE_URL + "/v1/getById?empId=" + EMPLOYEE_ID);
    }
}

// eslint-disable-next-line
export default new EmployeeService();