namespace ApiTuristas.Response
{

    public class ApiResponse<T>{
    public string Estado {get;set;}
    public string Codigo {get;set;}
    public string Mensaje {get;set;}
    public T Datos {get;set;}


    }

}