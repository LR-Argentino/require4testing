package org.blackbird.requirefortesting.authentication;

public interface SecurityService {

  String getCurrentUserId();

  boolean isUserRequirementEngineer();

  boolean isUserTester();
}
