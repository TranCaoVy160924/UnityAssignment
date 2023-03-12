using UnityEngine;
using UnityEngine.SceneManagement;

public class MainMenu : MonoBehaviour
{
    [SerializeField] private AudioSource ButtonClickSound;

    public void GoToSence(string senceName)
    {
        ButtonClickSound.Play();
        SceneManager.LoadScene(senceName);
    }

    public void QuitGame()
    {
        ButtonClickSound.Play();
        Application.Quit();
    }

    public void RestartLevel()
    {
        ButtonClickSound.Play();
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
