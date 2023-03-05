package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {
    
    public void save(Project project){
        String sql = "INSERT INTO projects (nameP, descriptionP, createdAt, updatedAt) VALUES (?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, project.getNameP());
            statement.setString(2, project.getDescriptionP());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.execute();
        }catch (Exception e){
            throw new RuntimeException("Erro ao salvar projeto " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void update(Project project, int id){
        String sql = "UPDATE projects SET nameP = ?, descriptionP = ?, createdAt = ?, updatedAt = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
         try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            
            statement.setString(1, project.getNameP());
            statement.setString(2, project.getDescriptionP());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, id);
            statement.execute();
            
         }catch (Exception e){
            throw new RuntimeException("Erro ao atualizar projeto " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    public void removeById(int idproject){
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try{
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idproject);
            statement.execute();
        }catch (Exception e){
            throw new RuntimeException("Erro ao deletar projeto " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }    
    }
    
    public List<Project> getAll(){
        String sql = "SELECT * FROM projects";
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<Project> projects = new ArrayList<Project>();
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()){
                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setNameP(resultSet.getString("nameP"));
                project.setDescriptionP(resultSet.getString("descriptionP"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                projects.add(project);
            }
            
        } catch (Exception e){
            throw new RuntimeException("Erro ao puxar todos os projetos " + e.getMessage());
        }finally {
            ConnectionFactory.closeConnection(conn, statement, resultSet);
        }
        return projects;
    }
}
