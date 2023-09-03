import React, { Component } from 'react';
import EmployeeService from '../service/EmployeeService';

class EmployeeComponent extends Component {

    constructor(props) {
        super(props);
        
        this.state = {
            employee: {},
            department: {},
            organization: {}
        }
    }
    
    componentDidMount() {
        EmployeeService.getEmployee().then((response) => {
            this.setState({employee : response.data.employeeDTO})
            this.setState({department : response.data.departmentDTO})
            this.setState({organization : response.data.organizationDTO})

            console.log(this.state.employee);
            console.log(this.state.department);
            console.log(this.state.organization)
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className='card col-md-6 offset-md-3'>

                    <h3 className='text-center card-header'>Employee Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                            <p><strong>Employee Name: </strong> {this.state.employee.employeeName} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Employee Email: </strong> {this.state.employee.employeeEmail} </p>
                        </div>
                    </div>

                    <h3 className='text-center card-header'>Department Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                            <p><strong>Department Name: </strong> {this.state.department.departmentName} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Department Description: </strong> {this.state.department.departmentDesc} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Department Code: </strong> {this.state.department.departmentCode} </p>
                        </div>
                    </div>

                    <h3 className='text-center card-header'>Organization Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                            <p><strong>Organization Name: </strong> {this.state.organization.organizationName} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Organization Description: </strong> {this.state.organization.organizationDescription} </p>
                        </div>
                        <div className='row'>
                            <p><strong>Organization Code: </strong> {this.state.organization.organizationCode} </p>
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}

export default EmployeeComponent;