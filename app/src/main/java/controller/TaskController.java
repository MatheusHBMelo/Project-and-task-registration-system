package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {
    
    public void save(Task task){
        String sql = "INSERT INTO tasks (id_project, nameT, descriptionT, isCompleted, notes, deadline, createdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, task.getId_project());
            statement.setString(2, task.getNameT());
            statement.setString(3, task.getDescriptionT());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
        }catch (Exception e){
            throw new RuntimeException("Erro ao salvar a tarefa " + e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void update (Task task){
        String sql = "UPDATE tasks SET id_project = ?, nameT = ?, descriptionT = ?, isCompleted = ?, notes = ?, "
                + "deadline = ?, createdAt = ?, updatedAt = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            
            statement.setInt(1, task.getId_project());
            statement.setString(2, task.getNameT());
            statement.setString(3, task.getDescriptionT());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();

            
        }catch (Exception e){
            throw new RuntimeException("Erro ao atualizar tarefa " + e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void removeById(int taskId){
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();
        }catch (Exception e){
            throw new RuntimeException("Erro ao deletar uma tarefa" + e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public List<Task> getAll(int idProject){
        String sql = "SELECT * FROM tasks WHERE id_project = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setId_project(resultSet.getInt("id_project"));
                task.setNameT(resultSet.getString("nameT"));
                task.setDescriptionT(resultSet.getString("descriptionT"));
                task.setIsCompleted(resultSet.getBoolean("isCompleted"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                tasks.add(task);
                
            }
        }catch (Exception e){
            throw new RuntimeException("Erro ao puxar todas as tarefas" + e.getMessage());
        } finally{
            ConnectionFactory.closeConnection(conn, statement, resultSet);
        }
        
        return tasks;
    }
}
