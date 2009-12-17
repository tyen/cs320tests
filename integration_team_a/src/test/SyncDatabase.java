package test;
import edu.cs320.project.StorageWrapper;

public class SyncDatabase {
	public static void main(String[] args) {
		StorageWrapper.Sync("cs320.allergy");
		StorageWrapper.Sync("cs320.drug");
		StorageWrapper.Sync("cs320.drug_questions");
		StorageWrapper.Sync("cs320.interacts_with");
		StorageWrapper.Sync("cs320.note");
		StorageWrapper.Sync("cs320.patient");
		StorageWrapper.Sync("cs320.question_set");
		StorageWrapper.Sync("cs320.takes");
		StorageWrapper.Sync("cs320.user");
	}

}
