package ru.decease.mailtest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CreateSendMailTests.class, CreateDraftMailTests.class})
public class MailTestsSuite {
}
