package webservice.services;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import webservice.entities.Note;
import webservice.entities.Notes;

import java.io.IOException;
import java.util.Collection;

public class NoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Notes mNotes;
    public NoteServlet() {
        super();
        mNotes= new Notes();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = mNotes.createNote(req.getParameter("title"), req.getParameter("content"), req.getParameter("authorFirstName"), req.getParameter("authorLastName"));
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getOutputStream().print(id);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = extractId(req);
        if(id != 0) {
            Note note = mNotes.readNote(id);
            if(note != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getOutputStream().println(note.toString());
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
            Collection<Note> allNotes = mNotes.readAll();
            for(Note n : allNotes) {
                resp.getOutputStream().println(n.toString());
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = extractId(req);
        if(id != 0) {
            if(id == mNotes.updateNote(id,req.getParameter("title"), req.getParameter("content"), req.getParameter("authorFirstName"), req.getParameter("authorLastName"))){
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getOutputStream().print(id);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

        } else resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = extractId(req);
        if(id != 0) {
            if(mNotes.deleteNote(id)) resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            else resp.setStatus(HttpServletResponse.SC_NOT_FOUND);


        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private long extractId(HttpServletRequest req) {
        long ret = 0;
        int index = req.getRequestURI().lastIndexOf("/");
        try {
            ret = Long.parseLong(req.getRequestURI().substring(index+1));
        } catch (NumberFormatException e) {
        }
        return ret;
    }
}
