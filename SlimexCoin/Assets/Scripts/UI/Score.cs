using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class Score : MonoBehaviour
{
    public static int ScoreValue = 0;
    TextMeshProUGUI score;
    // Start is called before the first frame update
    void Start()
    {
        score= GetComponent<TextMeshProUGUI>();
        ScoreValue = 0;
    }

    // Update is called once per frame
    void Update()
    {
        score.text = "Coins : " + ScoreValue;
        if (Movement.isDead)
        {
            score.gameObject.SetActive(false);
        }
        else
        if (Movement.isWin)
        {
            score.gameObject.SetActive(false);
        }
        else
        {
            score.gameObject.SetActive(true);
        }
    }
}
