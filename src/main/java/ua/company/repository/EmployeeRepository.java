package ua.company.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.company.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {
    Employee findByUserName(String employeeName);
}
