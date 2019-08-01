package controller;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.googlecode.objectify.Ref;
import entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class EmployeeController  extends HttpServlet {
    private static BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/employee/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setFullName(req.getParameter("fullName"));
        employee.setBirthday(req.getParameter("birthday"));
        employee.setAddress(req.getParameter("address"));
        employee.setPosition(req.getParameter("position"));
        employee.setDepartment(req.getParameter("department"));

        employee.setEmployeeRef(Ref.create(employee));

        ofy().save().entity(employee).now();
        resp.sendRedirect("/employee/list");
    }
}
