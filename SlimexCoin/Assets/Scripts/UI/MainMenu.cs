using UnityEngine;
using UnityEngine.SceneManagement;

public class MainMenu : MonoBehaviour
{
    public void GoToSence(string senceName)
    {
        SceneManager.LoadScene(senceName);
    }

    public void QuitGame()
    {
        Application.Quit();
        Debug.Log("Quit");
    }

    public void RestartLevel()
    {
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex);
    }

    public GameObject gameOverMenu;

    public void EnableGameOverMenu()
    {
        gameOverMenu.SetActive(true);
    }

    private void OnEnable()
    {
        Movement.onPlayerDeath += EnableGameOverMenu;
    }

    private void OnDisable()
    {
        Movement.onPlayerDeath -= EnableGameOverMenu;
    }
}
