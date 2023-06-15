package tdd.resources;

import jakarta.ws.rs.core.Application;

public class ApiTestUseFakeDB extends ApiTest {
    @Override
    protected Application configure() {
        return new tdd.ApplicationFakeDB();
    }
}
