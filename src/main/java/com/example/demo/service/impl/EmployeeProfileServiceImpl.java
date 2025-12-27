@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {
    private final EmployeeProfileRepository employeeRepo;
    
    public EmployeeProfileServiceImpl(EmployeeProfileRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    
    @Override
    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        EmployeeProfile entity = convertToEntity(dto);
        EmployeeProfile saved = employeeRepo.save(entity);
        return convertToDto(saved);
    }
    
    // Similar implementations for all CRUD methods matching test expectations
}
