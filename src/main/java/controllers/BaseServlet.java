package controllers;

import DAO.PlayerDAO;
import services.OngoingMatchesService;

import javax.servlet.http.HttpServlet;



public class BaseServlet extends HttpServlet {
    protected OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    protected PlayerDAO playerDAO = new PlayerDAO();

}
