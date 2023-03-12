using System.Collections;
using System.Collections.Generic;
using UnityEngine.SceneManagement;
using UnityEngine;

public class WinGame : MonoBehaviour
{
    public GameObject gameWinMenu;

    public void EnableGameWinMenu()
    {
        gameWinMenu.SetActive(true);
    }

    private void OnEnable()
    {
        Movement.onPlayerWin += EnableGameWinMenu;
    }

    private void OnDisable()
    {
        Movement.onPlayerWin -= EnableGameWinMenu;
    }
}
