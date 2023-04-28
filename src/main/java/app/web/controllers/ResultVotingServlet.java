package app.web.controllers;

import app.dto.*;
import app.service.api.IStatisticsService;
import app.service.fabrics.StatisticServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResultVotingServlet", urlPatterns = "/result")
public class ResultVotingServlet extends HttpServlet {
    private final IStatisticsService statisticsService;

    public ResultVotingServlet() {
        this.statisticsService = StatisticServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        AllStatisticDTO allSort = statisticsService.getStatistic();

        writer.write("<p>Результаты голосования за лучшего исполнителя:</p>");
        for (VoteCounterRaw<SingerDTO> singer : allSort.getSingers()) {
            writer.write("<p>" + singer.getItem() + ": " + singer.getCountVoice() + "</p>");
        }

        writer.write("<p>Результаты голосования за лучший жанр:</p>");
        for (VoteCounterRaw<GenreDTO> genre : allSort.getGenres()) {
            writer.write("<p>" + genre.getItem() + ": " + genre.getCountVoice() + "</p>");
        }

        writer.write("<p>О пользователе:</p>");
        for (AboutUserDTO aboutUser : allSort.getAboutUsers()) {
            writer.write("<p>" + aboutUser + "</p>");
        }

    }
}
