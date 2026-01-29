using ApiTuristas.Data;
using ApiTuristas.Models;
using Microsoft.EntityFrameworkCore;
using ApiTuristas.Response;

var builder = WebApplication.CreateBuilder(args);

// Agregar CORS
builder.Services.AddCors(options =>
{
    options.AddPolicy("PermitirAndroid",
        policy =>
        {
            policy
                .AllowAnyOrigin()
                .AllowAnyMethod()
                .AllowAnyHeader();
        });
});

// Cadena de conexión PostgreSQL
var connectionString = "Host=localhost;Port=5432;Database=api_turistas;Username=postgres;Password=postgres";

// Agregar DbContext
builder.Services.AddDbContext<AppDbContext>(options =>
    options.UseNpgsql(connectionString));

// Add services to the container.
builder.Services.AddOpenApi();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// Registrar controladores (MUY IMPORTANTE)
builder.Services.AddControllers();

var app = builder.Build();

// Usar CORS 
app.UseCors("PermitirAndroid");

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.MapOpenApi();       // Nuevo endpoint /openapi
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

var summaries = new[]
{
    "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
};

app.MapGet("/weatherforecast", () =>
{
    var forecast =  Enumerable.Range(1, 5).Select(index =>
        new WeatherForecast
        (
            DateOnly.FromDateTime(DateTime.Now.AddDays(index)),
            Random.Shared.Next(-20, 55),
            summaries[Random.Shared.Next(summaries.Length)]
        ))
        .ToArray();
    return forecast;
})
.WithName("GetWeatherForecast");

// MAPEAR CONTROLADORES
app.MapControllers();

app.Run();

record WeatherForecast(DateOnly Date, int TemperatureC, string? Summary)
{
    public int TemperatureF => 32 + (int)(TemperatureC / 0.5556);
}
