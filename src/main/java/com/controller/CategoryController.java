package com.controller;

import com.google.gson.Gson;
import com.model.Category;
import com.model.JsonResult;
import com.service.CategoryService;
import com.service_impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/api/v1/category/*")
public class CategoryController extends HttpServlet {

    private final CategoryService categoryService = new CategoryServiceImpl();

    private final JsonResult jsonResult = new JsonResult();

    private final Gson gson = new Gson();

    //xử lí những api them dữ liệu
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xử lí api liên quan đến thêm 1 bản ghi category
        String rs = null;
        String pathInfo=request.getPathInfo();
        if(pathInfo.equals("/post")) {
            try {
                // hàm insert cần truyền vào 1 đối tượng category

                //Cách truyền dữ liệu từ client lên server
                /**
                 * cách 1 : truyền qua param, các thông tin của ng dùng truyền lên bằng queryString: key = value
                 *  value : String, không truyền được dối tượng
                 *  cách 2 : truyền thong tin qua body
                 *          - Chỉ có trong các method : PUT, POST, DELETE.
                 *          - Có thể truyền được dối tươgnj
                 */
                // Cách lấy thông tin từ body bằng thư viện Gson
                // chuyển 1 chuỗi json thành đối tượng nhờ thư viện gson

                // Tham số đầu tiên của hàm fromJson có thể là 1 chuỗi json, hoặc là 1 bộ đọc để đọc chuỗi json
                //request.getReader() là bộ đọc dùng để đọc thông tin người dùng vào body
                //tham số thứ 2 là chuyển đối tượng json về category
                Category category = gson.fromJson(request.getReader(), Category.class);
                response.getWriter().println(category);
                rs = gson.toJson(jsonResult.jsonSuccess(categoryService.insert(category)));
            } catch (Exception ex) {
                ex.printStackTrace();
                rs = gson.toJson(jsonResult.jsonFail("Thêm category thất bại"));
            }
            response.getWriter().println(rs);
        }

    }


    //xử lí những api tìm kiếm dữ liệu
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //controller => service => dao
        /**
         * Đối với tìm kiếm dữ liệu trong service cung cấp 2 hàm :
         *      - findAll
         *      - findById
         *   - api/v1/category/find-all => findAll
         *   - api/v1/category/find-by-id => findById
         */
        String pathInfo = request.getPathInfo();
        String rs = null;
        if (pathInfo.equals("/find-all")) {
            //gọi đến service của category để lấy ra kết quả
            try {
                List<Category> list = categoryService.findAll();
                if (list != null) {
                    rs = gson.toJson(jsonResult.jsonSuccess(list));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                rs = gson.toJson(jsonResult.jsonFail("category find all fail"));
                // kết quả trả về luôn là 1 chuỗi
                // client không nhận biết được đây là thông tin
                // thực hiện thành công hay lỗi
                // để thể hiện api trả về đúng thông tin thì cần
                //ngoài chuỗi trả về thì cần thêm 1 trường
                // để người dùng biết được success hay fail
            }
        } else if (pathInfo.equals("/find-by-id")) {
            //gọi đến service findById
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                Category category = categoryService.findById(id);
                rs = gson.toJson(jsonResult.jsonSuccess(category == null ? "" : category));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                rs = gson.toJson(jsonResult.jsonFail("category find by id fail"));
            }

        } else {
            //404
            // int status là Http status code
            response.sendError(404, "Hi- chào cậu");
        }
        response.getWriter().println(rs);
    }

    // xử lí những api xóa dữ liệu
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs =null;
        try{
            int id =Integer.parseInt(request.getParameter("id"));
            if(categoryService.delete(id)){
                rs = gson.toJson(jsonResult.jsonSuccess("Success"));
            }else{
                 rs = gson.toJson(jsonResult.jsonFail("fail"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.getWriter().println(rs);

    }

    // xử lí những api xử lí dữ liệu
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rs = null;
        String pathInfo=request.getPathInfo();
        if(pathInfo.equals("/put")) {
            try {
                Category category = gson.fromJson(request.getReader(), Category.class);
                rs = gson.toJson(jsonResult.jsonSuccess(categoryService.Update(category)));
            } catch (Exception ex) {
                ex.printStackTrace();
                rs = gson.toJson(jsonResult.jsonFail("Update category thất bại"));
            }
            response.getWriter().println(rs);
        }
    }
}
