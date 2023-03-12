using System.Collections;
using System.Collections.Generic;
using Unity.VisualScripting;
using UnityEngine;

public class OnOffMusic : MonoBehaviour
{
    [SerializeField] AudioSource music;
    public static bool isOn = true;

    private void Awake()
    {
        if(isOn) music.Play();
        else music.Stop();
    }

    public void On()
    {
        isOn = true;
        music.Play();
    }

    public void Off()
    {
        isOn = false;
        music.Stop();
    }
}
