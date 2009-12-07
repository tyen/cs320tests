package tests;

import cuke4duke.*;
import cs320.*;
import static org.junit.Assert.*;

public class RoleSystem {
	@Given("^there is a user \"([^\"]*)\" with password \"([^\"]*)\"$")
	@Pending
	public void thereIsAUserAdminWithPasswordLetmein(String user, String password) {
		
	}
	
	@Given("^the user \"([^\"]*)\" has the role \"([^\"]*)\"$")
	@Pending
	public void theUserAdminHasTheRoleSystemAdministrator(String user, String userType) {
	}
	
	@Given("^the following permissions exist:$")
	@Pending
	public void theFollowingPermissionsExistWithTable(cuke4duke.Table table) throws Exception {
		//permission weren't implemented in Team A's code
		throw new Exception();
	}
	
	@When("^I create the role \"([^\"]*)\"$")
	@Pending
	public void iCreateTheRoleNurse(String role) {
	}
	
	@When("^grant permissions p1 to the role \"([^\"]*)\"$")
	@Pending
	public void grantPermissionsP1ToTheRoleNurse(String arg1) throws Exception {
		//permission weren't implemented in Team A's code
		throw new Exception();
	}
	
	@When("^remove all permissions from role \"([^\"]*)\"$")
	@Pending
	public void removeAllPermissionsFromRoleNurse(String arg1) {
	}
	
	@When("^deactivate the role \"([^\"]*)\"$")
	@Pending
	public void deactivateTheRoleNurse(String arg1) {
	}
	
	@Then("^the role \"([^\"]*)\" has 0 permissions$")
	@Pending
	public void theRoleNurseHas0Permissions(String arg1) {
	}
	
	@Then("^the role nurse is inactive$")
	@Pending
	public void theRoleNurseIsInactive() {
	}
	
	@When("^grant permissions p2 to the role \"([^\"]*)\"$")
	@Pending
	public void grantPermissionsP2ToTheRoleNurse(String arg1) {
	}
	
	@When("^grant permissions p3 to the role \"([^\"]*)\"$")
	@Pending
	public void grantPermissionsP3ToTheRoleNurse(String arg1) {
	}
	
	@When("^grant permissions p1,p2 to the role \"([^\"]*)\"$")
	@Pending
	public void grantPermissionsP1p2ToTheRoleNurse(String arg1) {
	}
	
	@When("^grant permissions p1,p3 to the role \"([^\"]*)\"$")
	@Pending
	public void grantPermissionsP1p3ToTheRoleNurse(String arg1) {
	}
	
	@When("^grant permissions p2,p3 to the role \"([^\"]*)\"$")
	@Pending
	public void grantPermissionsP2p3ToTheRoleNurse(String arg1) {
	}
	
	@When("^grant permissions p1,p2,p3 to the role \"([^\"]*)\"$")
	@Pending
	public void grantPermissionsP1p2p3ToTheRoleNurse(String arg1) {
	}
	
	@Given("^the roles \"([^\"]*)\" exist$")
	@Pending
	public void theRolesNurseDoctorPharmacistExist(String arg1) {
	}
	
	@Given("^the roles \"([^\"]*)\" have different permission sets$")
	@Pending
	public void theRolesNurseDoctorPharmacistHaveDifferentPermissionSets(String arg1) {
	}
	
	@Given("^there is a user karen with the password letmein$")
	@Pending
	public void thereIsAUserKarenWithThePasswordLetmein() {
	}
	
	@Given("^the user karen has the role Nurse$")
	@Pending
	public void theUserKarenHasTheRoleNurse() {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to karen$")
	@Pending
	public void iExecuteAllCreatePrivilegesGrantedToKaren(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to karen$")
	@Pending
	public void iExecuteAllReadPrivilegesGrantedToKaren(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to karen$")
	@Pending
	public void iExecuteAllUpdatePrivilegesGrantedToKaren(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to karen$")
	@Pending
	public void iExecuteAllDeletePrivilegesGrantedToKaren(String arg1) {
	}
	
	@Given("^there is a user eric with the password imadoctor$")
	@Pending
	public void thereIsAUserEricWithThePasswordImadoctor() {
	}
	
	@Given("^the user eric has the role Doctor$")
	@Pending
	public void theUserEricHasTheRoleDoctor() {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to eric$")
	@Pending
	public void iExecuteAllCreatePrivilegesGrantedToEric(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to eric$")
	@Pending
	public void iExecuteAllReadPrivilegesGrantedToEric(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to eric$")
	@Pending
	public void iExecuteAllUpdatePrivilegesGrantedToEric(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to eric$")
	@Pending
	public void iExecuteAllDeletePrivilegesGrantedToEric(String arg1) {
	}
	
	@Given("^there is a user ed with the password drugcheck$")
	@Pending
	public void thereIsAUserEdWithThePasswordDrugcheck() {
	}
	
	@Given("^the user ed has the role Pharmacist$")
	@Pending
	public void theUserEdHasTheRolePharmacist() {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to ed$")
	@Pending
	public void iExecuteAllCreatePrivilegesGrantedToEd(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to ed$")
	@Pending
	public void iExecuteAllReadPrivilegesGrantedToEd(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to ed$")
	@Pending
	public void iExecuteAllUpdatePrivilegesGrantedToEd(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges granted to ed$")
	@Pending
	public void iExecuteAllDeletePrivilegesGrantedToEd(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to karen$")
	@Pending
	public void iExecuteAllCreatePrivilegesNotGrantedToKaren(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to karen$")
	@Pending
	public void iExecuteAllReadPrivilegesNotGrantedToKaren(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to karen$")
	@Pending
	public void iExecuteAllUpdatePrivilegesNotGrantedToKaren(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to karen$")
	@Pending
	public void iExecuteAllDeletePrivilegesNotGrantedToKaren(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to eric$")
	@Pending
	public void iExecuteAllCreatePrivilegesNotGrantedToEric(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to eric$")
	@Pending
	public void iExecuteAllReadPrivilegesNotGrantedToEric(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to eric$")
	@Pending
	public void iExecuteAllUpdatePrivilegesNotGrantedToEric(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to eric$")
	@Pending
	public void iExecuteAllDeletePrivilegesNotGrantedToEric(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to ed$")
	@Pending
	public void iExecuteAllCreatePrivilegesNotGrantedToEd(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to ed$")
	@Pending
	public void iExecuteAllReadPrivilegesNotGrantedToEd(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to ed$")
	@Pending
	public void iExecuteAllUpdatePrivilegesNotGrantedToEd(String arg1) {
	}
	
	@Then("^I execute all \"([^\"]*)\" privileges not granted to ed$")
	@Pending
	public void iExecuteAllDeletePrivilegesNotGrantedToEd(String arg1) {
	}
	
	// 7500
	@Given("^\"([^\"]*)\" users with username \"([^\"]*)\" and password \"([^\"]*)\", where <i> ranges from 1 to 7500$")
	@Pending
	public void UsersWithUsernameUseriAndPasswordSecretpwiWhereiRangesFrom1To7500(String arg1, String arg2, String arg3) {
	}
	
	@Given("^\"([^\"]*)\" has a randomly selected role <role_name>$")
	@Pending
	public void useriHasARandomlySelectedRoleRoleName(String arg1) {
	}
	
	@When("^I login all users \"([^\"]*)\" with password \"([^\"]*)\"$")
	@Pending
	public void iLoginAllUsersUseriWithPasswordSecretpwi(String arg1, String arg2) {
	}
	
	@Then("^the computer's user environment corresponds with the correctly assigned role$")
	@Pending
	public void theComputersUserEnvironmentCorrespondsWithTheCorrectlyAssignedRole() {
	}
	
	@When("^I remove all useres \"([^\"]*)\"$")
	@Pending
	public void iRemoveAllUseresUseri(String arg1) {
	}
	
	@Then("^I expect to not be able to login as \"([^\"]*)\" with password \"([^\"]*)\"$")
	@Pending
	public void iExpectToNotBeAbleToLoginAsUserWithPasswordSecretpw(String arg1, String arg2) {
	}
	
	@Given("^a user account with role \"([^\"]*)\"$")
	@Pending
	public void aUserAccountWithRoleNurse(String arg1) {
	}
	
	@Given("^a user account with role \"([^\"]*)\"$")
	@Pending
	public void aUserAccountWithRoleDoctor(String arg1) {
	}
	
	@Given("^a user account with role \"([^\"]*)\"$")
	@Pending
	public void aUserAccountWithRolePharmacist(String arg1) {
	}
	
	@When("^I access all respective functions for each user account$")
	@Pending
	public void iAccessAllRespectiveFunctionsForEachUserAccount() {
	}
	
	@Then("^I expect each action to be logged correctly$")
	@Pending
	public void iExpectEachActionToBeLoggedCorrectly() {
	}
}
