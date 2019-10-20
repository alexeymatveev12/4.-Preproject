package filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SessionCheckFilter")
public class SessionCheckFilter implements Filter {

    private String contextPath;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        contextPath = fc.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {

//        HttpServletRequest req = (HttpServletRequest) request;
 //       HttpServletResponse resp = (HttpServletResponse) response;
  //      HttpSession session = req.getSession(); //для получения атрибута
  //      User userLogOn = (User) req.getSession().getAttribute("userTryLogin");
 //       System.out.println("print user from session filter - userTryLogin : " + userLogOn);
 //       System.out.println("print role: "+userLogOn.getRole() );
   //     User userLogOn = (User) session.getAttribute("userTryLogin");
   //     if (userLogOn == null) {
            //put your redirect stuff here
 //           resp.sendRedirect(contextPath + "/index.jsp");
   //     } else
 /*           if (userLogOn.getRole().equals("admin")) {


            req.setAttribute("userLogOn", userLogOn);
            session.setAttribute("userLogOn", userLogOn);
                System.out.println("admin: redirect" );
            resp.sendRedirect("read");
        } else if (userLogOn.getRole().equals("user")) {
            req.setAttribute("userLogOn", userLogOn);
            session.setAttribute("userLogOn", userLogOn);
                System.out.println("user: redirect" );
            resp.sendRedirect("user");
        }
           /* switch (user.getRole()) {
                case ADMIN:
                    //put your redirect stuff here
                    res.sendRedirect(contextPath + "/redirect_to_your_admin_path/admin_page.jsp");
                    break;
                case USER:
                    //put your redirect stuff here
                    res.sendRedirect(contextPath + "/redirect_to_staff_path/staff_page.jsp");
                    break;
                default:
                    break;
*/
 //          fc.doFilter(request, response);
//////////////////////////////////////////////////////////////////


        HttpServletRequest req = (HttpServletRequest) request;

        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(); //для получения атрибута


        //получаем атрибуты из сервлета ИЗ СЕССИИ???
        User userLogOn = (User) session.getAttribute("userTryLogin");
        System.out.println("print user from session filter - userTryLogin : " + userLogOn);
        String userRole = userLogOn.getRole();

        //перенаправляем на ReadServlet
        if (userRole.equals("admin")) {
            fc.doFilter(req, resp);
        }
        //перенаправляем на UserServlet
        else if (userRole.equals("user")) {
            req.setAttribute("userLogOn", userLogOn);

            System.out.println("print user from session to USER - userLogOn : " + ((User) session.getAttribute("userLogOn")));
            // кладем в атрибуты сессии атрибут user с именем пользователя
            // почему в сессию??
           session.setAttribute("userLogOn", userLogOn);

            System.out.println("print user from session to USER - userLogOn : " + ((User) session.getAttribute("userLogOn")));

            resp.sendRedirect("user");
        }
        //перенаправляем на LoginServlet
        else if (userRole == null) {
            req.getServletContext()
                    .getRequestDispatcher("/jsp/index.jsp")
                    .forward(req, resp);
        }


    }




    @Override
    public void destroy() {

    }


}










