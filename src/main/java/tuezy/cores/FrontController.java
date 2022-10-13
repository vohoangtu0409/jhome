package tuezy.cores;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import tuezy.Application;

@WebServlet(name = "FrontController", value = "/")
public class FrontController extends HttpServlet {
    private Application application;

    public void init() {
        this.application = new Application();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      this.application.handle(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}